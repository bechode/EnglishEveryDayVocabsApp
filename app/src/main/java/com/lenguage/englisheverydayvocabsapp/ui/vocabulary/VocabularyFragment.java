package com.lenguage.englisheverydayvocabsapp.ui.vocabulary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenguage.englisheverydayvocabsapp.data.entity.Vocabulary;
import com.lenguage.englisheverydayvocabsapp.data.model.Resultado;
import com.lenguage.englisheverydayvocabsapp.databinding.FragmentVocabBinding;

import java.util.List;

public class VocabularyFragment extends Fragment {

    private FragmentVocabBinding binding;
    private VocabularyAdapter vocabularyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentVocabBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvListaVocabs = binding.rvListaVocabs;
        rvListaVocabs.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        vocabularyAdapter = new VocabularyAdapter();
        rvListaVocabs.setAdapter(vocabularyAdapter);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvListaVocabs);

        VocabularyViewModel viewModel = new ViewModelProvider(this).get(VocabularyViewModel.class);
        viewModel.init();

        viewModel.getListaVocabulary().observe(getViewLifecycleOwner(), observerListaVocab);
        viewModel.getElResultado().observe(getViewLifecycleOwner(), observerResultado);

        binding.buttonAgregar.setOnClickListener(view1 -> {
            if(binding.edittextVocabulary.getText().toString().trim().length() > 0 && binding.edittextTranslate.getText().toString().trim().length() > 0) {
                Vocabulary v = new Vocabulary();
                v.setWord(binding.edittextVocabulary.getText().toString().trim());
                v.setSpanishTranslation(binding.edittextTranslate.getText().toString().trim());
                viewModel.insertarListaVocabulary(v);
            }

        });

        binding.edittextVocabulary.setText("");
        binding.edittextTranslate.setText("");

        viewModel.cargarListaVocabulary();
    }

    private Observer<List<Vocabulary>> observerListaVocab = new Observer<List<Vocabulary>>() {
        @Override
        public void onChanged(List<Vocabulary> vocabularies) {
            if(vocabularies != null) {
                vocabularyAdapter.addAll(vocabularies);
                binding.textviewCount.setText("Vocabularies count: " + vocabularyAdapter.getItemCount());
            }

        }
    };

    private Observer<Resultado<List<Vocabulary>>> observerResultado = new Observer<Resultado<List<Vocabulary>>>() {
        @Override
        public void onChanged(Resultado<List<Vocabulary>> listResultado) {
            if(listResultado != null) {
                if(listResultado.isAprobado()) {
                    Toast.makeText(getContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    if(listResultado.getItem() != null) {
                        List<Vocabulary> list = listResultado.getItem();
                        vocabularyAdapter.addAll(list);
                        //vocabularyAdapter.notifyDataSetChanged();
                    }
                    binding.textviewCount.setText("Vocabularies count: " + vocabularyAdapter.getItemCount());
                    binding.edittextVocabulary.setText("");
                    binding.edittextTranslate.setText("");
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //PedidoApp pedidoApp = pedidoViewModel.getPedidoApp().getValue();

            //if(pedidoApp == null) {
            //    pedidoApp = new PedidoApp();
            //    pedidoViewModel.getPedidoApp().setValue(pedidoApp);
            //}
            /*
            if(pedidoApp.getListaPedidoAppSimpleDet() != null) {
                try {
                    PedidoAppDetalle pd = pedidoApp.getListaPedidoAppSimpleDet().get(viewHolder.getAbsoluteAdapterPosition());
                    if(pd.getIdLocal() == 0) {
                        pedidoApp.getListaPedidoAppSimpleDet().remove(viewHolder.getAbsoluteAdapterPosition());
                        pedidoClienteAdapter.remove(viewHolder.getAbsoluteAdapterPosition());

                        if(pedidoClienteAdapter.getItemCount() == 0) {
                            contenedorMensaje.setVisibility(ConstraintLayout.VISIBLE);
                        }
                    }
                } catch (Exception ex) {
                }
            }
            */
        }
    };
}