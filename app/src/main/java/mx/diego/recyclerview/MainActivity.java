package mx.diego.recyclerview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.AdapterListener {

    private View coordinator;
    private RecyclerView recyclerView;
    private MyItemDecorator decorator;
    private MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinator = findViewById(R.id.coordinator);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MyRecyclerAdapter(createElements(), this);
        recyclerView.setAdapter(adapter);

        decorator = new MyItemDecorator(this, MyItemDecorator.VERTICAL_LIST);
        recyclerView.addItemDecoration(decorator);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<ItemClass> createElements() {
        List<ItemClass> elements = new ArrayList<>();
        for(int i = 0; i<40; i++){
            ItemClass element = new ItemClass();
            element.setTitle("Elemento " + i);
            element.setSubtitle(randomSubtitle());
            elements.add(element);
        }
        return elements;
    }

    private String randomSubtitle() {
        Random random = new Random();
        switch (random.nextInt(5)) {
            case 1:
                return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vel posuere enim. Praesent et accumsan sem. Proin scelerisque mi quis laoreet finibus.";
            case 2:
                return "Suspendisse in fringilla est. Morbi velit felis, aliquam ut scelerisque eu, hendrerit et neque. Vivamus sit amet neque quis libero gravida congue.";
            case 3:
                return "In ac dignissim eros, vel maximus urna. Duis non velit sit amet enim laoreet aliquet. Etiam quis turpis sem. Donec sed diam lectus.";
            case 4:
                return "Sed ultrices nunc vitae est lacinia varius. Etiam euismod lectus sed libero eleifend, id porta diam luctus. Sed at eleifend lorem.";
            default:
                return "Morbi interdum odio eu venenatis egestas. Vivamus pellentesque tellus vehicula ex posuere, a porttitor sem gravida. Maecenas sed libero ultricies, molestie turpis vel, dignissim metus.";
        }
    }

    @Override
    public void onClick(int position, ItemClass item) {
        Snackbar.make(coordinator, item.getTitle(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.managers, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.linear:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(decorator);
                adapter.setLayoutType(MyRecyclerAdapter.LINEAR);
                break;
            case R.id.grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.removeItemDecoration(decorator);
                adapter.setLayoutType(MyRecyclerAdapter.GRID);
                break;
            case R.id.staggered:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                recyclerView.removeItemDecoration(decorator);
                adapter.setLayoutType(MyRecyclerAdapter.GRID);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
